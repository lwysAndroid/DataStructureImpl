package com.example.firstapp.codingpatterns.linklists

import com.example.firstapp.printHyphensSeparation
import org.junit.Test
import kotlin.Int

class LinkListReversal {

    @Test
    fun testLinkListReversal() {
        printHyphensSeparation()
        testAndPrintLinkListReversal(valuesArray = arrayOf(1, 2, 4, 7, 3))
    }

    private fun testAndPrintLinkListReversal(valuesArray: Array<Int>) {
        val head = createLinkedListFromArray(valuesArray)
        if (head != null) {
            printLinkedList(head = head)
//            val reverseHead = linkListReversal(head = head)
            val reverseHead = linkListReversalRecursive(currentNode = head)
            printLinkedList(head = reverseHead)
            printHyphensSeparation()
        }
    }

    private fun linkListReversal(head: ListNode<Int>): ListNode<Int> {
        // We work around the current node knowing the previous and next node of it
        var previousNode: ListNode<Int>? = null
        var currentNode = head
        var nextNode = head.next

        // When the nextNode is null that means that it was the end of the original linked list
        while (nextNode != null) {
            // We update the pointers to point in the opposite direction
            currentNode.next = previousNode
            val nextNextNode = nextNode.next
            nextNode.next = currentNode

            // Wue update the current node and its adjacent nodes to continue updating the pointers
            previousNode = currentNode
            currentNode = nextNode
            nextNode = nextNextNode
        }

        return currentNode
    }

    private fun linkListReversalRecursive(
        previousNode: ListNode<Int>? = null,
        currentNode: ListNode<Int>,
        nextNode: ListNode<Int>? = currentNode.next
    ): ListNode<Int> {
        // Update Pointers
        currentNode.next = previousNode
        val nextNextNode = nextNode?.next
        nextNode?.next = currentNode

        /*
        * Base Case: Reaching a node where next is null indicates the end of the original
        * linked list. This terminal node becomes the head of the newly reversed list.
        */
        if (nextNode == null) {
            return currentNode
        }

        // Recursive case where we continue changing the pointers of the items of the linked list
        return linkListReversalRecursive(
            previousNode = currentNode,
            currentNode = nextNode,
            nextNode = nextNextNode
        )
    }
}