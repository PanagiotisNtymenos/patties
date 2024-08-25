package com.sicklibs.commons.utils.map

/**
 * Extension function to filter map entries with null values
 */
fun <K, V> Map<K, V>.filterNullValues(): Map<K, V> =
  filterValues { it == null }

/**
 * Extension function to map entries with non-null values
 */
fun <K, V> Map<K, V?>.filterOutNullValues(): Map<K, V> {
  val response = mutableMapOf<K, V>()

  mapValues { (key, value) ->
    if (value != null)
      response[key] = value
  }

  return response
}

/**
 * Extension function to invert a map
 */
fun <K, V> Map<K, V>.invert(): Map<V, K> =
  map { (key, value) -> value to key }.toMap()

/**
 * Extension function to filter map entries with duplicate values
 */
fun <K, V> Map<K, V>.filterDuplicateValues(): Map<K, V> =
  filterValues { value ->
    filterValues { it == value }.size > 1
  }

/**
 * Extension function to run a block of code if the map is not empty
 */
fun <K, V> Map<K, V>.alsoIfNotEmpty(block: (Map<K, V>) -> Map<K, V>) = also {
  if (isNotEmpty())
    block(this)
}