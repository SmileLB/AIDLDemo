// IMyAidlInterface.aidl
package com.example.smile.mydemo;

import com.example.smile.mydemo.Person;

// Declare any non-default types here with import statements

interface IMyAidlInterface {

    List<Person> add(in Person person);

}
