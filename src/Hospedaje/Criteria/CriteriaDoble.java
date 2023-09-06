package Hospedaje.Criteria;

import java.util.List;

public interface CriteriaDoble<T, S> {
    List<T> meetCriteria(List<T> habitaciones, List<S> reservas);
}
