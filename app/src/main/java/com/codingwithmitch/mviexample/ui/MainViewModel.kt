package com.codingwithmitch.mviexample.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.codingwithmitch.mviexample.model.BlogPost
import com.codingwithmitch.mviexample.model.User
import com.codingwithmitch.mviexample.repository.Repository
import com.codingwithmitch.mviexample.ui.StateEvent.*
import com.codingwithmitch.mviexample.util.AbsentLiveData
import com.codingwithmitch.mviexample.util.DataState

class MainViewModel : ViewModel(){

    protected val _stateEvent: MutableLiveData<StateEvent> = MutableLiveData()
    protected val _viewState: MutableLiveData<MainViewState> = MutableLiveData()

    val viewState: LiveData<MainViewState>
        get() = _viewState

    val dataState: LiveData<DataState<MainViewState>> = Transformations
        .switchMap(_stateEvent){stateEvent ->
            stateEvent?.let {
                handleStateEvent(stateEvent)
            }
        }

    fun handleStateEvent(stateEvent: StateEvent): LiveData<DataState<MainViewState>>{
        when(stateEvent){

            is GetBlogPostsEvent -> {
                return Repository.getBlogPosts()
            }

            is GetUserEvent -> {
                return Repository.getUser()
            }

            is None ->{
                return AbsentLiveData.create()
            }
        }
    }

    fun setStateEvent(event: StateEvent){
        val state: StateEvent
        state = event
        _stateEvent.value = state
    }

    fun setBlogListData(blogPosts: List<BlogPost>){
        val update = getCurrentViewStateOrNew()
        update.blogPosts = blogPosts
        _viewState.value = update
    }

    fun setUser(user: User){
        val update = getCurrentViewStateOrNew()
        update.user = user
        _viewState.value = update
    }

    fun getCurrentViewStateOrNew(): MainViewState{
        val value = viewState.value?.let{
            it
        }?: MainViewState()
        return value
    }

}

















