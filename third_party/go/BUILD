package(default_visibility = ["//visibility:public"])

licenses(["notice"])

load("//intellij_platform_sdk:build_defs.bzl", "select_for_plugin_api")

java_library(
    name = "go_internal",
    visibility = ["//visibility:private"],
    exports = select_for_plugin_api({
        "intellij-2018.1": ["@go_2018_1//:go"],
        "intellij-ue-2018.1": ["@go_2018_1//:go"],
        "intellij-2018.2": ["@go_2018_2//:go"],
        "intellij-ue-2018.2": ["@go_2018_2//:go"],
        "default": [],
    }),
)

java_library(
    name = "go_for_tests",
    testonly = 1,
    exports = [":go_internal"],
)

java_library(
    name = "go",
    neverlink = 1,
    exports = [":go_internal"],
)
