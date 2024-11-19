#!/bin/bash

# Define the old and new package names
OLD_PACKAGE="com.osedhelu.bnc"
NEW_PACKAGE="com.disglobal.bnc"

# Define the root directory of the project
PROJECT_ROOT="/Users/osedhelu/StudioProjects/__Disglobal__/nexgobnc"

# Find and replace the package name in all relevant files
find "$PROJECT_ROOT" -type f \( -name "*.kt" -o -name "*.xml" -o -name "*.kts" -o -name "build.gradle" -o -name "AndroidManifest.xml" \) -exec sed -i '' "s/$OLD_PACKAGE/$NEW_PACKAGE/g" {} +

# Update import statements in Kotlin files
find "$PROJECT_ROOT" -type f -name "*.kt" -exec sed -i '' "s/import $OLD_PACKAGE/import $NEW_PACKAGE/g" {} +

# Update package statements in Kotlin files
find "$PROJECT_ROOT" -type f -name "*.kt" -exec sed -i '' "s/package $OLD_PACKAGE/package $NEW_PACKAGE/g" {} +

# Rename the directories to match the new package name
OLD_PACKAGE_DIR=$(echo "$OLD_PACKAGE" | tr '.' '/')
NEW_PACKAGE_DIR=$(echo "$NEW_PACKAGE" | tr '.' '/')

mkdir -p "$PROJECT_ROOT/app/src/main/java/$NEW_PACKAGE_DIR"
mkdir -p "$PROJECT_ROOT/app/src/test/java/$NEW_PACKAGE_DIR"
mkdir -p "$PROJECT_ROOT/app/src/androidTest/java/$NEW_PACKAGE_DIR"

shopt -s dotglob
if [ -d "$PROJECT_ROOT/app/src/main/java/$OLD_PACKAGE_DIR" ] && [ "$(ls -A $PROJECT_ROOT/app/src/main/java/$OLD_PACKAGE_DIR)" ]; then
    mv "$PROJECT_ROOT/app/src/main/java/$OLD_PACKAGE_DIR"/* "$PROJECT_ROOT/app/src/main/java/$NEW_PACKAGE_DIR"
fi
if [ -d "$PROJECT_ROOT/app/src/test/java/$OLD_PACKAGE_DIR" ] && [ "$(ls -A $PROJECT_ROOT/app/src/test/java/$OLD_PACKAGE_DIR)" ]; then
    mv "$PROJECT_ROOT/app/src/test/java/$OLD_PACKAGE_DIR"/* "$PROJECT_ROOT/app/src/test/java/$NEW_PACKAGE_DIR"
fi
if [ -d "$PROJECT_ROOT/app/src/androidTest/java/$OLD_PACKAGE_DIR" ] && [ "$(ls -A $PROJECT_ROOT/app/src/androidTest/java/$OLD_PACKAGE_DIR)" ]; then
    mv "$PROJECT_ROOT/app/src/androidTest/java/$OLD_PACKAGE_DIR"/* "$PROJECT_ROOT/app/src/androidTest/java/$NEW_PACKAGE_DIR"
fi
shopt -u dotglob

# Remove old directories
rm -rf "$PROJECT_ROOT/app/src/main/java/$OLD_PACKAGE_DIR"
rm -rf "$PROJECT_ROOT/app/src/test/java/$OLD_PACKAGE_DIR"
rm -rf "$PROJECT_ROOT/app/src/androidTest/java/$OLD_PACKAGE_DIR"

# Remove build and other generated directories
rm -rf "$PROJECT_ROOT/app/build"
rm -rf "$PROJECT_ROOT/.gradle"
rm -rf "$PROJECT_ROOT/.idea"
rm -rf "$PROJECT_ROOT/build"

echo "Package name changed from $OLD_PACKAGE to $NEW_PACKAGE and cleaned build directories"