package com.p3solutions.a360gatewayserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@Component
@Primary
@EnableAutoConfiguration
public class DocumentationController implements SwaggerResourcesProvider {

  private final RouteDefinitionLocator locator;

  public static final String API_URI = "/api-docs";

  @Autowired
  public DocumentationController(RouteDefinitionLocator locator) {
    this.locator = locator;
  }


  @Override
  public List<SwaggerResource> get() {

    List<SwaggerResource> resources = new ArrayList<>();

    // Remove routes from DiscoveryClientRouteDefinitionLocator and construct swaggerResource

    List<RouteDefinition> list3 = new ArrayList<>();
    locator.getRouteDefinitions().collectList().subscribe(list3::addAll);
    list3.stream()
              .filter(routeDefinition -> routeDefinition.getId().contains("a360-"))
              .forEach(routeDefinition -> {
                String name = routeDefinition.getId().replaceAll("-service", "");
                resources.add(
                        swaggerResource(
                                name,
                                routeDefinition
                                        .getPredicates()
                                        .get(0)
                                        .getArgs()
                                        .get("_genkey_0")
                                        .replace("/**", API_URI)));
              });
    return resources;

  }

  private SwaggerResource swaggerResource(String name, String location) {
    SwaggerResource swaggerResource = new SwaggerResource();
    swaggerResource.setName(name);
    swaggerResource.setLocation(location);
    swaggerResource.setSwaggerVersion("2.0");
    return swaggerResource;
  }
}
