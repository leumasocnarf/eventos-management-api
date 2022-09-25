package br.com.senaceventos.Services.Common;


import java.util.List;


public interface IBaseService <T>{
    List<T> fetchAll();
    T fetchOne(Integer entityId);
    void append(T t);
    void remove(Integer entityId);
    void update(Integer entityId, T newT);
}
