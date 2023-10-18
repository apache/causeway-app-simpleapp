
if [ $# = 1 ]; then
  LATEST_VERSION=$1
else
  LATEST_VERSION=$(curl -s https://raw.githubusercontent.com/apache-causeway-committers/causeway-nightly/master/mvn-snapshots/org/apache/isis/isis-bom/maven-metadata.xml | grep '<version>' | tail -1 | cut -f2 -d'>' | cut -f1 -d'<')
fi

echo "parentVersion = $LATEST_VERSION"
mvn versions:update-parent -DparentVersion="[$LATEST_VERSION]"

CURR=$(grep "<causeway.version>" pom.xml | head -1 | cut -d'>' -f2 | cut -d'<' -f1)
sed -i "s|<causeway.version>$CURR</causeway.version>|<causeway.version>$LATEST_VERSION</causeway.version>|g" pom.xml

git add pom.xml
git commit -m "updates parent pom to $LATEST_VERSION"
