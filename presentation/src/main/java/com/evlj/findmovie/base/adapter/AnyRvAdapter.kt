package com.evlj.findmovie.base.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.evlj.findmovie.shared.extensions.tryOrNull
import kotlin.reflect.KClass

class AnyRvAdapter(
    private val controllers: Map<KClass<*>, AnyRvItemController<*>>,
    initialItems: List<Any> = emptyList(),
    private val onTouchEvent: (TouchEvent, Any) -> Boolean = { _, _ -> false }
) : RecyclerView.Adapter<AnyRvAdapter.ViewHolder>() {

    private val _items: MutableList<Any> = initialItems.toMutableList()

    @Suppress("UNCHECKED_CAST", "TYPE_INFERENCE_ONLY_INPUT_TYPES_WARNING", "unused")
    private val <T : Any> T.controller
        get() = tryOrNull { controllers[this::class] }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder =
        (_items.getOrNull(position)?.controller?.createView(parent)
            ?: View(parent.context))
            .let(::ViewHolder)

    override fun getItemCount(): Int = _items.size

    override fun getItemViewType(position: Int): Int = position

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        _items.getOrNull(position)?.let { item ->
            holder.bindRootListeners(item)
            item.controller?.safeBind(holder.rootView, item, onTouchEvent)
        }
    }

    private fun ViewHolder.bindRootListeners(item: Any) {
        rootView.apply {
            setOnClickListener {
                this@AnyRvAdapter.onTouchEvent(TouchEvent.RootClick, item)
            }

            setOnLongClickListener {
                this@AnyRvAdapter.onTouchEvent(TouchEvent.RootLongClick, item)
            }
        }
    }

    fun add(item: Any) {
        add(listOf(item))
    }

    fun add(items: List<Any>) {
        _items.let { oldList ->
            _items.addAll(items)
            notifyItemRangeInserted(oldList.size, oldList.size + items.size - 1)
        }
    }

    class ViewHolder(val rootView: View) : RecyclerView.ViewHolder(rootView)

    interface TouchEvent {
        object RootClick : TouchEvent
        object RootLongClick : TouchEvent
    }
}

abstract class AnyRvItemController<T : Any>() {
    abstract fun createView(parent: ViewGroup): View
    abstract fun bind(
        rootView: View,
        item: T,
        onTouchEvent: (AnyRvAdapter.TouchEvent, T) -> Boolean
    )

    @Suppress("UNCHECKED_CAST")
    internal fun safeBind(
        rootView: View,
        item: Any,
        onTouchEvent: (AnyRvAdapter.TouchEvent, T) -> Boolean
    ) {
        tryOrNull { bind(rootView, item as T, onTouchEvent) }
    }
}