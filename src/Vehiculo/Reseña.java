package Vehiculo;

public class Reseña {


    private String opinion;
    private int calificacion;

    public Reseña(String opinion, int calificacion) {
        this.opinion = opinion;
        this.calificacion = calificacion;
    }

    public void editarOpinion(String nuevaOpinion, int nuevaCalificacion) {
        this.opinion = nuevaOpinion;
        this.calificacion = nuevaCalificacion;

    }

    @Override
    public String toString() {
        return opinion + " " + calificacion + "\n";
    }


}
