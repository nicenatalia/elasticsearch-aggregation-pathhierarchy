package org.opendatasoft.elasticsearch.plugin;

import java.util.ArrayList;
import org.elasticsearch.plugins.Plugin;
import org.elasticsearch.plugins.SearchPlugin;
import org.opendatasoft.elasticsearch.search.aggregations.bucket.PathHierarchyAggregationBuilder;
import org.opendatasoft.elasticsearch.search.aggregations.bucket.DateHierarchyAggregationBuilder;
import org.opendatasoft.elasticsearch.search.aggregations.bucket.InternalPathHierarchy;
import org.opendatasoft.elasticsearch.search.aggregations.bucket.InternalDateHierarchy;

public class PathHierarchyAggregation  extends Plugin implements SearchPlugin {
    @Override
    public ArrayList<SearchPlugin.AggregationSpec> getAggregations() {
        ArrayList<SearchPlugin.AggregationSpec> r = new ArrayList<>();

        r.add(
                new AggregationSpec(
                        PathHierarchyAggregationBuilder.NAME,
                        PathHierarchyAggregationBuilder::new,
                        PathHierarchyAggregationBuilder::parse)
                        .addResultReader(InternalPathHierarchy::new)
                        .setAggregatorRegistrar(PathHierarchyAggregationBuilder::registerAggregators)
        );
        r.add(
                new AggregationSpec(
                        DateHierarchyAggregationBuilder.NAME,
                        DateHierarchyAggregationBuilder::new,
                        DateHierarchyAggregationBuilder::parse)
                        .addResultReader(InternalDateHierarchy::new)
                        .setAggregatorRegistrar(DateHierarchyAggregationBuilder::registerAggregators)
        );
        return r;
    }
}
