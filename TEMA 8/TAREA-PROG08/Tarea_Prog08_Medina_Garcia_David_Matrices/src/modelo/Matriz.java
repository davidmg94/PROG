package modelo;

/**
 *
 * @author David Medina Garcia
 */
public class Matriz {

    public final static int DIMENSION_MAX = 7;

    private int dimension;
    private int[][] matriz;

    // Constructor por defecto que crea una matriz de 7x7 con todos los elementos a 0
    public Matriz() {
        this.dimension = DIMENSION_MAX;
        this.matriz = new int[dimension][dimension];
        inicializarMatrizA0();
    }

    // Constructor que crea una matriz de dimension x dimension con valores aleatorios
    // de 2 dígitos que no sean 0
    public Matriz(int dimension) {
        if (!validarDimension(dimension)) {
            this.dimension = 7;
        } else {
            this.dimension = dimension;
        }
        this.matriz = new int[dimension][dimension];
        inicializarMatrizAleatoria();
    }

    // getters y setters
    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        if (!validarDimension(dimension)) {
            this.dimension = 7;
        } else {
            this.dimension = dimension;
        }
    }

    public int[][] getMatriz() {
        return matriz;
    }

    // Validar que la dimensión sea positiva y nunca mayor que 7
    public static boolean validarDimension(int dimension) {
        return dimension <= DIMENSION_MAX || dimension > 0;
    }

    // Método privado para inicializar la matriz con todos los elementos a 0
    private void inicializarMatrizA0() {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = 0;
            }
        }
    }

    // Método privado para inicializar la matriz con todos los elementos aleatorios
    private void inicializarMatrizAleatoria() {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = (int) (Math.random() * 99 + 1);
            }
        }
    }

    public void setValor(int i, int j, int valor) {
        if (i < 0 || i >= dimension || j < 0 || j >= dimension) {
            throw new IllegalArgumentException("Índices fuera de rango.");
        } else {
            matriz[i][j] = valor;
        }
    }

    public int getValor(int i, int j) {
        if (i < 0 || i >= dimension || j < 0 || j >= dimension) {
            throw new IllegalArgumentException("Índices fuera de rango.");
        } else {
            return matriz[i][j];
        }
    }

    // suma posiciones diagonales
    public int diagonal() {
        int suma = 0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (i == j) {
                    suma += matriz[i][j];
                }
            }
        }

        return suma;
    }

    // matriz transpuesta
    public Matriz transpuesta() {
        Matriz transpuesta = new Matriz(this.dimension);
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                transpuesta.setValor(j, i, matriz[i][j]);
            }
        }
        return transpuesta;
    }

    // suma de matrices
    public Matriz suma(Matriz m) {
        Matriz sumaMatrices = new Matriz(dimension);
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                sumaMatrices.setValor(i, j, this.matriz[i][j] + m.getValor(i, j));
            }
        }
        return sumaMatrices;
    }

    // suma de matrices
    public Matriz multiplicar(Matriz m) {
        Matriz sumaMatrices = new Matriz(dimension);
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                sumaMatrices.setValor(i, j, this.matriz[i][j] * m.getValor(i, j));
            }
        }
        return sumaMatrices;
    }

    // suma de una fila dada
    public int sumaFila(int fila) {
        int suma = 0;

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (i == fila) {
                    suma += matriz[i][j];
                }
            }
        }

        return suma;
    }

    // suma de una columna dada 
    public int sumaColumna(int columna) {
        int suma = 0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (j == columna) {
                    suma += matriz[i][j];
                }
            }
        }

        return suma;
    }
}
