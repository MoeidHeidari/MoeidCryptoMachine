package com.example.cryptomachine;


import com.example.cryptomachine.Annotations.ShouldBeEncrypted;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class CipherMan implements CipherManView {


    private Class classType;
    private String key;
    private String IV;
    private Object object;
    private CipherMode cipherMode;


    private CipherMan() {

    }

    private static CipherMan cipher;


    public static CipherMan with() {
        cipher = new CipherMan();
        return cipher;
    }

    @Override
    public CipherMan setKey(String key) {
        cipher.key = key;

        return cipher;
    }

    @Override
    public CipherMan setIV(String IV) {
        cipher.IV = IV;
        return cipher;
    }

    @Override
    public CipherMan setObject(Object object) {
        cipher.object = object;
        return cipher;
    }

    @Override
    public CipherMan setClassType(Class type) {
        cipher.classType = type;
        return cipher;
    }

    @Override
    public CipherMan setMode(CipherMode mode) {


        cipher.cipherMode = mode;
        return cipher;
    }

    @Override
    public void doTheOperation() throws IllegalAccessException, InvalidateKeyOrIvException {


        if (key.length() < 16 || IV.length() < 16) {
            throw new InvalidateKeyOrIvException("Invalide Key or IV");

        }


        if (cipherMode == CipherMode.ECRYPT) {
            for (Field field : classType.getDeclaredFields()) {
                if (field.isAnnotationPresent(ShouldBeEncrypted.class)) {
                    field.setAccessible(true);


                    field.set(object, Encryptor.encrypt(key, IV, (String) field.get(object)));


                }
                if (field.getType() == List.class) {


                    ParameterizedType pType = (ParameterizedType) field.getGenericType();


                    Class<?> clazz = (Class<?>) pType.getActualTypeArguments()[0];

                    for (Field SubFieldfield : clazz.getDeclaredFields()) {


                        if (SubFieldfield.isAnnotationPresent(ShouldBeEncrypted.class)) {

                            SubFieldfield.setAccessible(true);


                            List<Object> value = (List<Object>) field.get(object);

                            for (Object values : value)
                            {

                                SubFieldfield.set(values, Encryptor.encrypt(key, IV, (String) SubFieldfield.get(values)));
                            }


                        }
                    }


                }
            }

        } else if (cipherMode == CipherMode.DECRYPT) {
            for (Field field : classType.getDeclaredFields()) {
                if (field.isAnnotationPresent(ShouldBeEncrypted.class)) {
                    field.setAccessible(true);
                    field.set(object, Encryptor.decrypt(key, IV, (String) field.get(object)));

                }
                if (field.getType() == List.class) {

                    ParameterizedType pType = (ParameterizedType) field.getGenericType();

                    Class<?> clazz = (Class<?>) pType.getActualTypeArguments()[0];

                    for (Field SubFieldfield : clazz.getDeclaredFields()) {


                        if (SubFieldfield.isAnnotationPresent(ShouldBeEncrypted.class)) {

                            SubFieldfield.setAccessible(true);

                            List<Object> value = (List<Object>) field.get(object);

                            for (Object values : value)
                                SubFieldfield.set(values, Encryptor.decrypt(key, IV, (String) SubFieldfield.get(values)));


                        }
                    }


                }
            }

        }


    }
}
