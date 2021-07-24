//
//  AppAssembler.swift
//  iosApp
//
//  Created by Uwais Alqadri on 24/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation

protocol Assembler: BrowseAssembler,
                    MyMangaAssembler,
                    SavedAssembler {}

class AppAssembler: Assembler {}
