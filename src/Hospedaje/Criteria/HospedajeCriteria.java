package Hospedaje.Criteria;

import java.util.List;

public interface HospedajeCriteria<T, S> {
    List<T> meetCriteria(List<T> habitaciones, List<S> reservas);
}
