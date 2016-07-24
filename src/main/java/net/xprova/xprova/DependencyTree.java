package net.xprova.xprova;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.TreeSet;

import net.xprova.graph.Graph;

public class DependencyTree {

	public static ArrayList<TreeSet<String>> depen(HashMap<String, HashSet<String>> dependencies) throws Exception {

		Graph<String> d = getGraph(dependencies);

		// find designs with zero dependencies (atoms)

		TreeSet<String> atoms = new TreeSet<String>();

		for (String design : d.getVertices()) {

			if (d.getDestinations(design).isEmpty())
				atoms.add(design);

		}

		// build hierarchy

		String top = "topunit";

		ArrayList<TreeSet<String>> hierarchy = new ArrayList<TreeSet<String>>();

		HashSet<String> current = new HashSet<String>();

		current.addAll(atoms);

		int vertexCount = d.getVertexCount();

		while (!current.isEmpty()) {

			hierarchy.add(new TreeSet<String>(current));

			for (String s : current)
				d.removeVertex(s);

			// check if vertexCount decreased

			int newVertexCount = d.getVertexCount();

			if (newVertexCount == vertexCount) {

				throw new Exception(
						"Error while analyzing dependencies, possible circular dependencies exist in design");

			} else {

				vertexCount = newVertexCount;

			}

			// now look for vertices with no destinations

			current.clear();

			for (String s : d.getVertices()) {

				if (d.getDestinations(s).isEmpty()) {

					current.add(s);

					if (top.equals(s)) {

						return hierarchy;

					}

				}

			}

		}

		throw new Exception("Could not determine dependency hierachy for design " + top);

	}

	public static void printDependencies(HashMap<String, HashSet<String>> dependencies) throws Exception {

		Graph<String> d = getGraph(dependencies);

		for (String design : d.getVertices()) {

			HashSet<String> depends = d.getDestinations(design);

			if (depends.isEmpty()) {

				System.out.printf("Design %s is an atom.\n", design);

			} else {

				System.out.printf("Design %s has %d dependencies:\n", design, depends.size());

				for (String module : depends) {

					System.out.printf("  - %s\n", module);

				}

			}

		}
	}

	public static Graph<String> getGraph(HashMap<String, HashSet<String>> dependencies) throws Exception {

		// this method takes a HashMap of dependencies where each entry is
		// <designName, HashSet of dependencies>
		// and returns a graph where A -> B means A depends on B

		Graph<String> d = new Graph<String>();

		for (Entry<String, HashSet<String>> entry : dependencies.entrySet()) {

			d.addVertex(entry.getKey());

			String design = d.getVertex(entry.getKey());

			for (String module : entry.getValue()) {

				d.addVertex(module);

				d.addConnection(design, module);

			}

		}

		return d;

	}

}
