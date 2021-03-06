/*
 * Copyright 2017 The Bazel Authors. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.idea.blaze.cpp;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.idea.blaze.base.ideinfo.CToolchainIdeInfo;
import javax.annotation.concurrent.Immutable;

/**
 * Resolve configuration maps, etc. obtained from running the {@link BlazeConfigurationResolver}.
 */
@Immutable
final class BlazeConfigurationResolverResult {

  final ImmutableMap<BlazeResolveConfigurationData, BlazeResolveConfiguration>
      uniqueResolveConfigurations;
  final ImmutableMap<CToolchainIdeInfo, BlazeCompilerSettings> compilerSettings;

  private BlazeConfigurationResolverResult(
      ImmutableMap<BlazeResolveConfigurationData, BlazeResolveConfiguration>
          uniqueResolveConfigurations,
      ImmutableMap<CToolchainIdeInfo, BlazeCompilerSettings> compilerSettings) {
    this.uniqueResolveConfigurations = uniqueResolveConfigurations;
    this.compilerSettings = compilerSettings;
  }

  static Builder builder() {
    return new Builder();
  }

  static BlazeConfigurationResolverResult empty() {
    return builder().build();
  }

  ImmutableList<BlazeResolveConfiguration> getAllConfigurations() {
    return uniqueResolveConfigurations.values().asList();
  }

  static class Builder {
    ImmutableMap<BlazeResolveConfigurationData, BlazeResolveConfiguration> uniqueConfigurations =
        ImmutableMap.of();
    ImmutableMap<CToolchainIdeInfo, BlazeCompilerSettings> compilerSettings = ImmutableMap.of();

    public Builder() {}

    BlazeConfigurationResolverResult build() {
      return new BlazeConfigurationResolverResult(uniqueConfigurations, compilerSettings);
    }

    void setUniqueConfigurations(
        ImmutableMap<BlazeResolveConfigurationData, BlazeResolveConfiguration>
            uniqueConfigurations) {
      this.uniqueConfigurations = uniqueConfigurations;
    }

    void setCompilerSettings(
        ImmutableMap<CToolchainIdeInfo, BlazeCompilerSettings> compilerSettings) {
      this.compilerSettings = compilerSettings;
    }
  }
}
