package com.example.cryptomachine;

public interface CipherManView {

    CipherMan setKey(String key);

    CipherMan setIV(String IV);

    CipherMan setObject(Object object);

    CipherMan setClassType(Class type);

    CipherMan setMode(CipherMode mode);

    void doTheOperation() throws IllegalAccessException, InvalidateKeyOrIvException;
}
