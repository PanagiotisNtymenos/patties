package com.sicklibs.ktel.bus

import com.sicklibs.ktel.command.Command

/**
 * Command bus interface
 */
interface CommandBus<C : Command<R>, R> {

  /**
   * Routes the command to the appropriate handler
   *
   * @param command the command to route
   * @return the response of the handler
   */
  fun route(command: C): R
}