package main.java.ru.vsu.cs.course1.graph;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Реализация графа на основе матрицы смежности
 */
public class AdjMatrixGraph implements Graph {

    private boolean[][] adjMatrix = null;
    private int vCount = 0;
    private int eCount = 0;

    /**
     * Конструктор
     * @param vertexCount Кол-во вершин графа (может увеличиваться при добавлении ребер)
     */
    public AdjMatrixGraph(int vertexCount) {
        adjMatrix = new boolean[vertexCount][vertexCount];
        vCount = vertexCount;
    }
    
    /**
     * Конструктор без парметров
     * (лучше не использовать, т.к. при добавлении вершин каждый раз пересоздается матрица)
     */
    public AdjMatrixGraph() {
        this(0);
    }

    @Override
    public int vertexCount() {
        return vCount;
    }

    @Override
    public int edgeCount() {
        return eCount;
    }

    @Override
    public void addAdge(int v1, int v2) {
        int maxV = Math.max(v1, v2);
        if (maxV >= vertexCount()) {
            adjMatrix = Arrays.copyOf(adjMatrix, maxV + 1);
            for (int i = 0; i <= maxV; i++) {
                adjMatrix[i] = i < vCount ? Arrays.copyOf(adjMatrix[i], maxV + 1) : new boolean[maxV + 1];
            }
            vCount = maxV + 1;
        }
        if (!adjMatrix[v1][v2]) {
            adjMatrix[v1][v2] = true;
            adjMatrix[v2][v1] = true;
            eCount++;
        }
    }
    
    @Override
    public void removeAdge(int v1, int v2) {
        if (adjMatrix[v1][v2]) {
            adjMatrix[v1][v2] = false;
            adjMatrix[v2][v1] = false;
            eCount--;
        }
    }

    @Override
    public Iterable<Integer> adjacencies(int v) {
        return new Iterable<Integer>() {
            Integer nextAdj = null;

            @Override
            public Iterator<Integer> iterator() {
                for (int i = 0; i < vCount; i++) {
                    if (adjMatrix[v][i]) {
                        nextAdj = i;
                        break;
                    }
                }

                return new Iterator<Integer>() {
                    @Override
                    public boolean hasNext() {
                        return nextAdj != null;
                    }

                    @Override
                    public Integer next() {
                        Integer result = nextAdj;                        
                        nextAdj = null;
                        for (int i = result + 1; i < vCount; i++) {
                            if (adjMatrix[v][i]) {
                                nextAdj = i;
                                break;
                            }
                        }
                        return result;
                    }
                };
            }
        };
    }
    
    public int solution() {
        int[][] intData = new int[this.vCount][this.vCount];
        for (int i = 0; i < this.vCount; i++) {
            for (int j = 0; j < this.vCount; j++) {
                intData[i][j] = 2147483647;
            }
            intData[i][i] = 0;
        }
        boolean[] isVisited;
        for (int i = 0; i < this.vCount; i++) {
            int length = 1;
            isVisited = new boolean[this.vCount];
            for (int j = 0; j < this.vCount; j++) {
                isVisited[j] = true;
                if (this.adjMatrix[i][j]) {
                    intData[i][j] = length;
                    depthCrawl(intData, length, i, j, isVisited);
                }
                isVisited[j] = false;
            }
        }
        int result = maxInt(intData);
        return result;
    }

    private void depthCrawl(int[][] intData, int length, int correctedLine, int changingLine, boolean[] isVisited) {
        length++;
        for (int j = 0; j < this.vCount; j++) {
            if (this.adjMatrix[changingLine][j] && !isVisited[j]) {
                isVisited[j] = true;
                if (length < intData[correctedLine][j]) {
                    intData[correctedLine][j] = length;
                }
                depthCrawl(intData, length, correctedLine, j, isVisited);
                isVisited[j] = false;
            }
        }
    }

    private int maxInt(int[][] intData) {
        int max = -1;
        for (int i = 0; i < this.vCount; i++) {
            for (int j = 0; j < this.vCount; j++) {
                if (intData[i][j] > max) {
                    max = intData[i][j];
                }
            }
        }
        return max;
    }
}
