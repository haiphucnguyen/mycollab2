package com.mycollab.vaadin.mvp

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
class PageActionChain(vararg pageActionArr: ScreenData<*>) {
    private val chains = mutableListOf<ScreenData<*>>()

    init {
        chains.addAll(pageActionArr)
    }

    fun add(pageAction: ScreenData<*>): PageActionChain {
        chains.add(pageAction)
        return this
    }

    fun pop(): ScreenData<*>? {
        return if (chains.size > 0) {
            val pageAction = chains[0]
            chains.removeAt(0)
            pageAction
        } else null
    }

    fun peek(): ScreenData<*> = chains[0]

    fun hasNext(): Boolean = chains.size > 0
}