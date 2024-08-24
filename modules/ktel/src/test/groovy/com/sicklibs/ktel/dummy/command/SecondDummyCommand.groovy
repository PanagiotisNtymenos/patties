package com.sicklibs.ktel.dummy.command

import com.sicklibs.ktel.command.Command

class SecondDummyCommand implements Command<String> {
  String attribute

  SecondDummyCommand(String attribute) {
    this.attribute = attribute
  }
}
