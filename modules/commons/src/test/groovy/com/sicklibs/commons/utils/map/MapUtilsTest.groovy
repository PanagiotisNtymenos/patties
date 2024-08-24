package com.sicklibs.commons.utils.map

import com.sicklibs.commonstest.helpers.UnitTest
import kotlin.Pair
import spock.lang.Shared
import spock.lang.Unroll

import static com.sicklibs.commons.utils.map.MapUtilsKt.alsoIfNotEmpty
import static com.sicklibs.commons.utils.map.MapUtilsKt.filterDuplicateValues
import static com.sicklibs.commons.utils.map.MapUtilsKt.filterNullValues
import static com.sicklibs.commons.utils.map.MapUtilsKt.filterOutNullValues
import static com.sicklibs.commons.utils.map.MapUtilsKt.invert
import static com.sicklibs.commons.utils.map.MapUtilsKt.toPairs

class MapUtilsTest extends UnitTest {

  @Shared
  String randomString = randomString()

  @Shared
  Integer randomInt = randomInt()

  // filterNullValues
  @Unroll
  def "Should filter entries with null values when filterNullValues is invoked"() {
    when:
      Map response = filterNullValues(source)

    then:
      response == expected

    where:
      source                                                   || expected
      [:]                                                      || [:]
      [(randomString()): randomString()]                       || [:]
      [(randomString()): null]                                 || source
      [(randomString()): randomString(), (randomString): null] || [(randomString): null]
  }

  // filterOutNullValues
  @Unroll
  def "Should filter out entries with null values when filterOutNullValues is invoked"() {
    when:
      Map response = filterOutNullValues(source)

    then:
      response == expected

    where:
      source                                              || expected
      [:]                                                 || [:]
      [(randomString()): null]                            || [:]
      [(randomString()): randomInt()]                     || source
      [(randomString()): null, (randomString): randomInt] || [(randomString): randomInt]
  }

  // invert
  @Unroll
  def "Should invert a map when invert is invoked"() {
    when:
      Map response = invert(source)

    then:
      response == expected

    where:
      source                      || expected
      [:]                         || [:]
      [(randomString): randomInt] || [(randomInt): randomString]
  }

  // toPairs
  @Unroll
  def "Should convert a map into a list of pairs when toPairs is invoked"() {
    when:
      List<Pair> response = toPairs(source)

    then:
      response == expected

    where:
      source                      || expected
      [:]                         || []
      [(randomString): randomInt] || [new Pair(randomString, randomInt)]
  }

  // filterDuplicateValues
  @Unroll
  def "Should filter entries with duplicate values when filterDuplicateValues is invoked"() {
    when:
      Map response = filterDuplicateValues(source)

    then:
      response == expected

    where:
      source                                                                                    || expected
      [:]                                                                                       || [:]
      [(randomString()): randomString()]                                                        || [:]
      [(randomString()): randomString(), (randomString()): randomString()]                      || [:]
      [(randomString()): randomString, (randomString()): randomString]                          || source
      [(randomString()): randomString, (randomString()): randomString, deleted: randomString()] || source.findAll { it.key != 'deleted' }
  }

  // alsoIfNotEmpty
  @Unroll
  def "Should execute the given block when map is not empty and alsoIfNotEmpty is invoked"() {
    given:
      Boolean blockExecuted = false

    when:
      Map response = alsoIfNotEmpty(source) { blockExecuted = true }

    then:
      response == source

    and:
      blockExecuted == expected

    where:
      source                             || expected
      [:]                                || false
      [(randomString()): randomString()] || true
  }
}
