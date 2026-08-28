package com.example.firstapp.codingpatterns.linklists

import com.example.firstapp.printHyphensSeparation
import org.junit.Test

class RemoveKthLastNode {

    @Test
    fun testRemoveKthLastNode() {
        printHyphensSeparation()

        val testArray = arrayOf(5, 4, 3, 2, 1)

        (0..5).forEach { kthLastNode ->
            testAndPrintRemoveKthLastNode(array = testArray, kthLastNode = kthLastNode)
        }
        testAndPrintRemoveKthLastNode(array = arrayOf(1), kthLastNode = 1)
//        testAndPrintRemoveKthLastNode(array = testArray, kthLastNode = 6)

    }

    private fun testAndPrintRemoveKthLastNode(array: Array<Int>, kthLastNode: Int) {
        val head = createLinkedListFromArray(array = array)
        head?.let {
            printLinkedList(head = head)
            val linkedListAfterRemoving = removeKthLastNode(head = it, kthLastNode = kthLastNode)
            println("linked List After Removing kth Last Node: $kthLastNode")
            printLinkedList(head = linkedListAfterRemoving)
            printHyphensSeparation()
        }
    }

    private fun removeKthLastNode(head: ListNode<Int>, kthLastNode: Int): ListNode<Int>? {
        var beforeKthLastNode = head
        var currentNode = head
        var counterNode = 1

        while (currentNode.next != null) {
            currentNode = currentNode.next!!
            counterNode++
            if (counterNode > (kthLastNode + 1)) {
                beforeKthLastNode = beforeKthLastNode.next!!
            }
        }

        when {
            counterNode == kthLastNode -> {
                return head.next
            }

            counterNode < kthLastNode -> {
                throw Exception("The kthLastNode $kthLastNode is out of bounds, linked list size: $counterNode")
            }

            else -> {
                beforeKthLastNode.next = beforeKthLastNode.next?.next
            }

        }
        return head
    }

}
