package com.example.projectreactor.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.*;
import java.util.function.Function;

public class FluxAndMonoServices {
     public Flux<String> fruitsFlux(){
    	 
    	 //logs Flux life cycle events such as subscription, item emissions(onNext), completion 
    	 //(onComplete) and errors (onError) to the console by default unless custom log configuration 
    	 //Spring Boot uses Logback by default and logging behavior can be customized using 
    	 // 'application.properties' or 'logback.xml'
         return Flux.fromIterable(List.of("Appy", "Mango", "Orange")).log();
     }

    public Flux<String> fruitsFluxMap(){
        return Flux.fromIterable(List.of("Appy", "Mango", "Orange"))
                .map(String::toUpperCase)
                .log();
    }

    public Flux<String> fruitsFluxFilter(int x){
         return Flux.fromIterable(List.of("Appy", "Mango", "Orange"))
                 .filter(s -> s.length() > x)
                 .log();
    }

    public Flux<String> fruitsFluxFilterMap(int x){
         return Flux.fromIterable(List.of("Appy", "Mango", "Orange"))
                 .filter(s -> s.length() > x)
                 .map(String::toUpperCase)
                 .log();
    }

    public Flux<String> fruitsFluxFlatMap(){
         return Flux.fromIterable(List.of("Appy", "Mango", "Orange"))
                 .flatMap(s -> Flux.just(s.split("")))
                 .log();
    }

    public Flux<String> fruitsFluxFlatMapAsync(){
        return Flux.fromIterable(List.of("Appy", "Mango", "Orange"))
                .flatMap(s -> Flux.just(s.split("")))
                .delayElements(Duration.ofMillis(new Random().nextInt(1000)))
                .log();
    }

    public Flux<String> fruitsFluxConcatMap(){
         return Flux.fromIterable(List.of("Appy", "Mango", "Orange"))
                 .concatMap(s -> Flux.just(s.split("")))
                 .delayElements(Duration.ofMillis(new Random().nextInt(1000)))
                 .log();
    }

    public Flux<String> fruitsFluxTransform(int number){
         Function<Flux<String>, Flux<String>> filterData = data -> data.filter(s -> s.length() > number);
         return Flux.fromIterable(List.of("Appy", "Mango", "Orange"))
                 .transform(filterData)
                 .log();
                //.filter(s -> s.length() > number);
    }

    public Mono<String> fruitMono(){
         return Mono.just("Appy").log();
     }

    public Mono<List<String>> fruitMonoFlatMap(){
         return Mono.just("Mango")
                 .flatMap(s -> Mono.just(List.of(s.split(""))))
                 .log();
    }

    public Flux<String> fruitMonoFlatMapMany(){
        return Mono.just("Mango")
                .flatMapMany(s -> Flux.just(s.split("")))
                .log();
    }
    
    // Overriding the toString() method to provide a meaningful string 
    //representation. By default, all Java objects is inherited from Object class
    // which contains toString() that returns ClassName@HexadecimalHashCode
    @Override
    public String toString() {
    	return "FluxAndMonoService instance";
    }

     public static void main(String[] args){
         FluxAndMonoServices fluxAndMonoServices = new FluxAndMonoServices();

         fluxAndMonoServices.fruitsFlux().subscribe(
                 s -> {
                     System.out.println("fruitsFlux = " + s);
                 }
         );

         fluxAndMonoServices.fruitMono().subscribe(
                 s -> {
                     System.out.println("fruitMono = " + s);
                 }
         );
         
         fluxAndMonoServices.fruitsFluxMap().subscribe(
        		 s -> {System.out.println("fruitsFluxMap = " + s);}
        		 
        		 );
		 
		 fluxAndMonoServices.fruitsFluxFilter(5).subscribe(
				 s -> {
					 System.out.println("fruitsFluxFilter with >5 Chars= " + s);
				 }
		 );
		 
		 fluxAndMonoServices.fruitsFluxFilterMap(4).subscribe(
             s -> {System.out.println("fruitsFluxFilterMap with >4 chars = " + s);}
             );
                        		 
		 fluxAndMonoServices.fruitsFluxFlatMap().subscribe(
				 s -> {
					 System.out.println("fruitsFluxFlatMap = " + s);
				 }
		 );
		 
		 fluxAndMonoServices.fruitsFluxFlatMapAsync().subscribe(
				 s -> { System.out.println("fruitsFluxFlatMapAsync = " + s); 
				  }
				 );

         fluxAndMonoServices.fruitsFluxConcatMap().subscribe(
				 s -> {System.out.println("fruitsFluxConcatMap = " + s);}
				 );

		 fluxAndMonoServices.fruitsFluxTransform(4).subscribe(
				 s -> {
					 System.out.println("fruitsFluxTransform with chars > 4 = " + s);
				 }
		 );
		 
		 fluxAndMonoServices.fruitMonoFlatMap().subscribe(
				 s -> { System.out.println("fruitMonoFlatMap = " + s); }
				 );
		 
		 fluxAndMonoServices.fruitMonoFlatMapMany().subscribe(
				 s -> { System.out.println("fruitMonoFlatMapMany = " + s); }
				 );

		 fluxAndMonoServices.fruitsFluxFilterMap(4).subscribe(
			 s -> {System.out.println("fruitsFluxFilterMap with >4 chars = " + s);}
			 );

         fluxAndMonoServices.fruitMonoFlatMap().subscribe(
        		 s -> {System.out.println("fruitMonoFlatMap = " + s);}
				 );  
     }
}
