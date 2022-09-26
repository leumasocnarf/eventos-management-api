package br.com.senaceventos.Controllers.Common;


import org.springframework.http.ResponseEntity;

import java.util.List;


public interface IBaseController<T> {
    ResponseEntity<List<T>> getListOf();
    ResponseEntity<T> get(Integer id);
    ResponseEntity<?> post(T t);
    ResponseEntity<?> put(Integer id, T t);
    ResponseEntity<?> delete(Integer id);
}
