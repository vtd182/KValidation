package org.example.core
class ValidationContext {
    private val observers = mutableListOf<Observer>()
    private val errorMap: MutableMap<String, MutableList<ValidationError>> = mutableMapOf()

    fun addObserver(observer: Observer) {
        observers.add(observer)
    }

    fun removeObserver(observer: Observer) {
        observers.remove(observer)
    }

    fun notifyObservers() {
        observers.forEach { it.update(errorMap) }
    }

    fun addError(field: String, error: ValidationError) {
        errorMap.computeIfAbsent(field) { mutableListOf() }.add(error)
        notifyObservers()
    }

    fun getErrors(): Map<String, List<ValidationError>> = errorMap

    fun clearErrors() {
        errorMap.clear()
        notifyObservers()
    }
}

interface Observer {
    fun update(errors: Map<String, List<ValidationError>>)
}
