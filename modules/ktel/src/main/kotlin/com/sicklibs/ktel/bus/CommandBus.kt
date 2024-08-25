package com.sicklibs.ktel.bus

import com.sicklibs.ktel.command.Command

/**
 * Command bus interface
 */
interface CommandBus {

  /**
   * Routes the command to the appropriate handler
   *
   * @param command the command to route
   * @return the response of the handler
   */
  fun <C : Command<R>, R> route(command: C): R
}