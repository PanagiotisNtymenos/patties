package com.sicklibs.commonstest.helpers

trait TestHelper {
  String randomString() {
    randomString(10)
  }

  String randomString(int length) {
    Random random = new Random()
    List chars = ('a'..'z') + ('A'..'Z') + ('0'..'9')
    return (1..length).collect { chars[random.nextInt(chars.size())] }.join()
  }

  Integer randomInt() {
    randomInt(100000)
  }

  Integer randomInt(int length) {
    new Random().nextInt(length)
  }
}