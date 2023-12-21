package com.example.tasklist.base.extensions

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.tasklist.base.base_entity.Event
import com.example.tasklist.base.base_entity.ResourceState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch


fun <K> StateFlow<Event<ResourceState<K>>>.collectEventState(): Flow<ResourceState<K>> {
    return this.mapNotNull{
        it.contentIfNotHandled?.let {
            return@mapNotNull it
        } ?: run{
            return@mapNotNull null
        }
    }
}

fun <K> StateFlow<Event<ResourceState<K>>>.collectResource(): Flow<ResourceState<K>> {
    return this.mapNotNull{
        it.mContent?.let {
            return@mapNotNull it
        } ?: run{
            return@mapNotNull null
        }
    }
}

@Composable
fun <K> Flow<ResourceState<K>>.asState(): ResourceState<K> {
    val data by this.collectAsState(initial = ResourceState(false, false, false))
    return data
}


data class ValidatorItemControl(
    val isValid: Boolean = true,
    val errorMessage: String = "",
    val value: String = "",
    val onValueChange : (String)->Unit = {},
    val errorChecker : (List<ValidatorItemControl>, String)->String? = { a, b -> null}
)

data class ValidatorFormControl(
    val onSubmit: () -> Unit,
    val state : List<MutableState<ValidatorItemControl>>
)

@Composable
fun ComposeValidatorForm(
    vararg stateValidations: ValidatorItemControl,
    onValid: (List<ValidatorItemControl>) -> Unit
): ValidatorFormControl {

    var dataState : ValidatorFormControl? = null

    fun onUpdateValue(value: String, state: MutableState<ValidatorItemControl>) {
        state.value = state.value.copy(value = value)
    }

    val formStateList: ArrayList<MutableState<ValidatorItemControl>> =
        arrayListOf<MutableState<ValidatorItemControl>>().apply {
            stateValidations.forEach {
                val state = mutableStateOf(it)
                val valueChange = { value: String ->
                    onUpdateValue(value, state)
                }
                state.value = state.value.copy(onValueChange = valueChange)
                this.add(state)
            }
        }

    val onSubmit = {
        var isValid = true
        stateValidations.forEachIndexed { index, validator ->
            val current = formStateList[index].value
            val message = validator.errorChecker(
                dataState?.state?.map { it.value } ?: listOf(),
                current.value
            )
            isValid = message.isNullOrBlank() == true
            formStateList[index].value = current.copy(
                isValid, message ?: ""
            )
        }
        if (isValid) onValid(formStateList.map { it.value })
    }

    dataState = ValidatorFormControl(onSubmit, formStateList)
    return remember { dataState }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun rememberTeaBottomSheetHidden(): ModalBottomSheetState {
    return rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
}

fun CoroutineScope.go(
    dispatch: CoroutineDispatcher = Dispatchers.Main,
    onDo: suspend ()->Unit
){
    launch(dispatch){
        onDo()
    }
}