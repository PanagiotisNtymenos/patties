package com.sicklibs.ktel.dummy.command

import com.sicklibs.ktel.command.Command

class FirstDummyCommand implements Command<String> {
  String attribute

  FirstDummyCommand(String attribute) {
    this.attribute = attribute
  }
}
