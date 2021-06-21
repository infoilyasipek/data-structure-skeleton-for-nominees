package kotlinversion

fun main() {
    var node: Node? = null
    val graph = buildGraph()
    val factoryCoordinate = Coordinate(7, 3)
    val shortestPathTree = dijkstra(graph, factoryCoordinate)
    val customers = getCustomers()
    customers.forEach {
        val shortestPath = shortestPathTree.findShortestPath(factoryCoordinate, it)
        if (node == null) {
            node = Node(shortestPath)
        } else {
            node!!.appendToEnd(shortestPath)
        }
    }
    println("----------------------------")
    node?.printNodes()

    println("----------------------------")
    println("Node's length: ${node?.length()}")

    println("----------------------------")
    println("Total cost: ${node?.sumOfNodes()}")

    val n = node?.deleteNode(node!!, 11)
    println("Deleted node ${n?.data}")

    println("----------------------------")
    node?.printNodes()
}

private fun buildGraph(): Graph<Coordinate> {
    val weights = mutableMapOf<Pair<Coordinate, Coordinate>, Int>()
    for (i in 0..10) {
        for (j in 0..10) {
            weights[Pair(
                Coordinate(x = i, y = j),
                Coordinate(x = i, y = j),
            )] = 0
        }
    }

    for (i in 0..10) {
        for (j in 1..10) {
            weights[Pair(
                Coordinate(x = i, y = (j - 1).coerceAtLeast(0)),
                Coordinate(x = i, y = j),
            )] = 1

            weights[Pair(
                Coordinate(x = i, y = j),
                Coordinate(x = i, y = (j - 1).coerceAtLeast(0)),
            )] = 1

            weights[Pair(
                Coordinate(x = (j - 1).coerceAtLeast(0), y = i),
                Coordinate(x = j, y = i),
            )] = 1

            weights[Pair(
                Coordinate(x = j, y = i),
                Coordinate(x = (j - 1).coerceAtLeast(0), y = i),
            )] = 1
        }
    }

    return Graph(weights)
}

private fun getCustomers(): List<Coordinate> {
    return listOf(
        Coordinate(1, 2),
        Coordinate(2, 5),
        Coordinate(2, 9),
        Coordinate(4, 1),
        Coordinate(4, 8),
        Coordinate(5, 6),
        Coordinate(9, 8),
        Coordinate(10, 1),
    )
}

data class Coordinate(val x: Int, val y: Int)
