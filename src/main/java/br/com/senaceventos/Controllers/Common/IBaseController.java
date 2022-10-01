package br.com.senaceventos.Controllers.Common;


import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;


public interface IBaseController<T> {
    ResponseEntity<List<T>> getListOf();
    ResponseEntity<T> get(Integer id);
    ResponseEntity<T> post(T t);
    ResponseEntity<T> put(Integer id, T t);
    ResponseEntity<Optional<T>> delete(Integer id);
}
