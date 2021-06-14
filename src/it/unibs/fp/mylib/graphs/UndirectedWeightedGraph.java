package it.unibs.fp.mylib.graphs;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import it.unibs.fp.mylib.graphs.edgestype.UndirectedWeightedEdge;

public class UndirectedWeightedGraph<N extends DefaultNode, V extends UndirectedWeightedEdge<N>> extends Graph<N, V> {
  public Set<V> getEdgesByNode(N node) {
    HashSet<V> edges_found = new HashSet<>();

    for (V edge : getAllEdges()) {
      if (edge.getFirstNode().equals(node) || edge.getSecondNode().equals(node))
        edges_found.add(edge);
    }

    return edges_found;
  }

  public V getEdge(N first_node, N second_node, double weight) {
    if (first_node.equals(second_node))
      return null;

    UndirectedWeightedEdge<N> edge_to_find = new UndirectedWeightedEdge<>(first_node, second_node, weight);

    for (V edge : getAllEdges()) {
      if (edge.equals(edge_to_find))
        return edge;
    }

    return null;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof UndirectedWeightedGraph) {
      UndirectedWeightedGraph<?, ?> graph_obj = (UndirectedWeightedGraph<?, ?>) obj;

      if (getAllNodes().size() != graph_obj.getAllNodes().size()
          || getAllEdges().size() != graph_obj.getAllEdges().size())
        return false;

      Iterator<N> this_nodes_iterator = getAllNodes().iterator();
      Iterator<?> graph_obj_nodes_iterator = graph_obj.getAllNodes().iterator();

      while (this_nodes_iterator.hasNext()) {
        if (!this_nodes_iterator.next().equals(graph_obj_nodes_iterator.next()))
          return false;
      }

      Iterator<V> this_edges_iterator = getAllEdges().iterator();
      Iterator<?> graph_obj_edges_iterator = graph_obj.getAllEdges().iterator();

      while (this_edges_iterator.hasNext()) {
        if (!this_edges_iterator.next().equals(graph_obj_edges_iterator.next()))
          return false;
      }

      return true;
    }

    return false;
  }
}