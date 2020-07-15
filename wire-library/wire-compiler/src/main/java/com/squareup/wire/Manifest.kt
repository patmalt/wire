/*
 * Copyright 2020 Square Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.squareup.wire

import com.charleskorn.kaml.Yaml
import com.google.common.graph.GraphBuilder
import com.google.common.graph.Graphs
import com.google.common.graph.ImmutableGraph
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer

data class Manifest(
  val compilationUnits: Map<String, Module>,
  val dependencyGraph: ImmutableGraph<String>
) {
  companion object {
    private val serializer = MapSerializer(String.serializer(), Module.serializer())

    fun fromYaml(string: String): Manifest {
      val map = Yaml.default.parse(serializer, string)

      // Check for cyclic dependencies.
      val dependencyGraph = GraphBuilder.directed().build<String>()
      map.forEach { name, module ->
        dependencyGraph.addNode(name)

        module.dependencies.forEach { dependency ->
          dependencyGraph.putEdge(dependency, name)
          require(!Graphs.hasCycle(dependencyGraph)) {
            "$name's dependency on $dependency forms a cycle"
          }
        }
      }

      return Manifest(map, ImmutableGraph.copyOf(dependencyGraph))
    }
  }
}

@Serializable
data class Module(
  val dependencies: Set<String> = emptySet(),
  val includes: Set<String> = setOf("*"),
  val excludes: Set<String> = emptySet()
)
