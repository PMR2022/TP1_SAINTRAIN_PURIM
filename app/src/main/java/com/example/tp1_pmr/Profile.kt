package com.example.tp1_pmr

class Profile(private var login: String = "", private var Lists: MutableList<ListTD> = mutableListOf<ListTD>()){
    constructor(Lists: MutableList<ListTD>) : this("",Lists);
    fun getLists(): MutableList<ListTD>{
        return Lists;
    }
    fun setLists(newLists: MutableList<ListTD>){
        Lists = newLists.toMutableList();
    }
    fun addList(newList: ListTD){
        Lists.add(newList);
    }
    fun getLogin(): String{
        return login;
    }
    fun setLogin(newLogin: String){
        login = newLogin;
    }
    override fun toString(): String{
        return "User "+login+": "+Lists.size+" lists";
    }
}