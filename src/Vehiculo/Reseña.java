package Vehiculo;

public class Reseña {


        private String opinion;
        private int calificacion;

        public Reseña(String opinion, int calificacion) {
                this.opinion = opinion;
                this.calificacion = calificacion;
        }

        public void editarOpinion(){
                //TODO implementar o eliminar

        }

        @Override
        public String toString() {
                return opinion + " " + calificacion + "\n";
        }


}
