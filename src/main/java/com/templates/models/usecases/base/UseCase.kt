package com.templates.models.usecases.base

interface UseCase<Input, Output> {
    fun execute(input: Input): Output
}