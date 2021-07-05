//
//  RecipeListViewModel.swift
//  iosApp
//
//  Created by Daniel Schmidt on 05.07.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

class RecipeListViewModel: ObservableObject {
    
    //Dependencies
    let searchRecipes: SearchRecipes
    let foodCategoryUtil: FoodCategoryUtil
    
    @Published var state: RecipeListState = RecipeListState()
    
    
    init(searchRecipes: SearchRecipes, foodCategoryUtil: FoodCategoryUtil) {
        self.searchRecipes = searchRecipes
        self.foodCategoryUtil = foodCategoryUtil
        
        //TODO perform search
    }
    
    
    
}
