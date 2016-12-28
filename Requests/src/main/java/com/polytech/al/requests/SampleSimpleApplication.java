/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.polytech.al.requests;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableFeignClients
@EnableDiscoveryClient
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class SampleSimpleApplication implements CommandLineRunner {

	// Simple example shows how a command line spring application can execute an
	// injected bean service. Also demonstrates how you can use @Value to inject
	// command line args ('--name=whatever') or application properties


	@Override
	public void run(String... args) {
		/*zoneRepository.deleteAll();
		playlistRepository.deleteAll();
		List<Song> songs = new ArrayList<Song>();
        songs.add(new Song("Music1.mp3",348));
        songs.add(new Song("Music.mp3",203));
		ZoneRequests p = new ZoneRequests("3",songs);
		List<ZoneRequests> requests = new ArrayList<ZoneRequests>();
		requests.add(p);
		Zone z = new Zone("0","Marrackech",3.0f,3.0f,p);
		//p.setZone(z);
		playlistRepository.save(p);
		zoneRepository.save(z);

		songs.clear();
		songs.add(new Song("Music3.mp3",248));
		songs.add(new Song("Music4.mp3",254));
		p = new ZoneRequests("4",songs);
		z = new Zone("1","Truc",4,4,p);
		playlistRepository.save(p);
		zoneRepository.save(z);
*/
	}

	public static void main(String[] args) throws Exception {

		SpringApplication.run(SampleSimpleApplication.class, args);
	}
}
