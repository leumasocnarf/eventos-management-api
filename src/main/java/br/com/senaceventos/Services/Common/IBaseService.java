package br.com.senaceventos.Services.Common;



import java.util.List;


public interface IBaseService <T>{
    List<T> retrieveAll();
    T retrieveById(Integer entityId);
    void append(T t);
    void alter(Integer entityId, T newT);
    void remove(Integer entityId);
}
