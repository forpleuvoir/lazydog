package moe.forpleuvoir.lazydog.task

import net.minecraft.client.MinecraftClient
import net.minecraft.server.MinecraftServer
import java.util.concurrent.ConcurrentLinkedQueue

abstract class TickTaskScheduler<T> {

    private val startTasks = ConcurrentLinkedQueue<TickTask<T>>()
    private val startRemoveList = ConcurrentLinkedQueue<TickTask<T>>()
    private val endTasks = ConcurrentLinkedQueue<TickTask<T>>()
    private val endRemoveList = ConcurrentLinkedQueue<TickTask<T>>()

    fun scheduleStartTick(task: TickTask<T>) {
        startTasks.add(task)
    }

    fun scheduleStartTick(action: (T) -> Unit) {
        startTasks.add(TickTask(0, 1, 1, action))
    }

    fun scheduleEndTick(task: TickTask<T>) {
        endTasks.add(task)
    }

    fun remove(task: TickTask<T>) {
        startRemoveList.add(task)
        endRemoveList.add(task)
    }

    fun clear() {
        startTasks.clear()
        endTasks.clear()
    }

    private fun endRemoveHandler() {
        endRemoveList.forEach {
            endTasks.remove(it)
        }
        endRemoveList.clear()
    }

    private fun startRemoveHandler() {
        startRemoveList.forEach {
            startTasks.remove(it)
        }
        startRemoveList.clear()
    }


    fun startTick(context: T) {
        startRemoveHandler()
        val iterator = startTasks.iterator()
        while (iterator.hasNext()) {
            val value = iterator.next()
            if (value.isOver) {
                iterator.remove()
            } else {
                value.tryExecute(context)
            }
        }
    }

    fun endTick(context: T) {
        endRemoveHandler()
        val iterator = endTasks.iterator()
        while (iterator.hasNext()) {
            val value = iterator.next()
            if (value.isOver) {
                iterator.remove()
            } else {
                value.tryExecute(context)
            }
        }
    }


    object Client : TickTaskScheduler<MinecraftClient>()

    object Server : TickTaskScheduler<MinecraftServer>()

}


