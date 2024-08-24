package com.sicklibs.ktel.handler

import com.sicklibs.ktel.command.Command

/**
 * [CommandHandler]
 */
interface CommandHandler<C : Command<R>, R> {

  /**
   * Handles the command
   *
   * @param command the command to handle
   * @return the response of handling the command
   */
  fun handle(command: C): R
}