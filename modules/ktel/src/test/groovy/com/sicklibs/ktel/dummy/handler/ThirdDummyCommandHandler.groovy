package com.sicklibs.ktel.dummy.handler

import com.sicklibs.ktel.dummy.command.FirstDummyCommand
import com.sicklibs.ktel.handler.CommandHandler

class ThirdDummyCommandHandler implements CommandHandler<FirstDummyCommand, String> {

  @Override
  String handle(FirstDummyCommand command) {
    return "Third handler handled command with attribute: ${command.attribute}"
  }
}
