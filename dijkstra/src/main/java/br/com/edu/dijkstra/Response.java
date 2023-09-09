package br.com.edu.dijkstra;

import java.util.List;

public record Response(Double cost, List<String> bestWay) {
}
