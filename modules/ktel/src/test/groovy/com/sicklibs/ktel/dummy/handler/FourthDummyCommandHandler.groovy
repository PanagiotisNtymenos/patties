package com.sicklibs.ktel.dummy.handler

import com.sicklibs.ktel.dummy.command.SecondDummyCommand
import com.sicklibs.ktel.handler.CommandHandler

class FourthDummyCommandHandler implements CommandHandler<SecondDummyCommand, String> {

  @Override
  String handle(SecondDummyCommand command) {
    return "Fourth handler handled command with attribute: ${command.attribute}"
  }
}
