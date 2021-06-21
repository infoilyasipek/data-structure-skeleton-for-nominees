package kotlinversion

class Node(var data: Int) {

    var following: Node? = null

    fun appendToEnd(data: Int) {
        val end = Node(data)
        var n: Node? = this
        while (n!!.following != null) {
            n = n.following
        }
        n.following = end
    }

    fun printNodes() {
        println(getNodes().joinToString(" --> ", transform = { it.data.toString() }))
    }

    fun length(h: Node? = this): Int {
        return getNodes(h).size
    }

    fun sumOfNodes(): Int {
        return getNodes().sumBy { it.data }
    }

    fun deleteNode(head: Node, data: Int): Node? {
        var n: Node = head
        var deletedNode: Node? = null
        while (n.following != null) {
            if (n.following!!.data == data) {
                deletedNode = n.following!!
                n.following = n.following!!.following
                return deletedNode
            }
            n = n.following!!
        }
        return deletedNode
    }

    private fun getNodes(node: Node? = this): List<Node> {
        var n: Node? = node
        val nodes = mutableListOf<Node>()
        while (n!!.following != null) {
            nodes.add(n)
            n = n.following
        }
        nodes.add(n)
        return nodes
    }
}