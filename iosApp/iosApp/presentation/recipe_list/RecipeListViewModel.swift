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
        
        onTriggerEvent(stateEvnt: RecipeListEvents.LoadRecipes())
    }
    
    func onTriggerEvent(stateEvnt: RecipeListEvents) {
        switch (stateEvnt) {
        case is RecipeListEvents.LoadRecipes:
            loadRecipes()
        case is RecipeListEvents.NewSearch:
            doNothing()
        case is RecipeListEvents.NextPage:
            doNothing()
        case is RecipeListEvents.OnUpdateQuery:
            doNothing()
        case is RecipeListEvents.OnSelectCategory:
            doNothing()
        case is RecipeListEvents.OnRemoveHeadMessageFromQueue:
            doNothing()
        default: doNothing()
        }
    }
    
    private func loadRecipes() {
        let currentState = self.state.copy() as! RecipeListState
        do {
            try searchRecipes.execute(
                page: currentState.page,
                query: currentState.query
            ).collectCommon(
                coroutineScope: nil,
                callback: { dataState in
                    if dataState != nil {
                        let data = dataState?.data
                        let message = dataState?.message
                        let loading = dataState?.isLoading ?? false
                        
                        self.updateState(isLoading: loading)
                        
                        if (data != nil) {
                            self.appendRecipes(recipes: data as! [Recipe])
                        }
                        if message != nil {
                            self.handleMessageByUIComponentType(message!)
                        }
                    }
                }
            )
            
        } catch {
            print("\(error)")
        }
    }
    
    func appendRecipes(recipes: [Recipe]) {
        for recipe in recipes {
            print("\(recipe)")
        }
        //TODO append
    }
    
    private func handleMessageByUIComponentType(_ message: GenericMessageInfo) {
        //TODO
    }
    
    func doNothing() {}
    
    func updateState(
        isLoading: Bool? = nil,
        page: Int? = nil,
        query: String? = nil,
        queue: Queue<GenericMessageInfo>? = nil
    ) {
        let currentState = self.state.copy() as! RecipeListState
        self.state = self.state.doCopy(isLoading: isLoading ?? currentState.isLoading,
                                       page: Int32(page ?? Int(currentState.page)),
                                       query: query ?? currentState.query,
                                       selectedCategory: currentState.selectedCategory,
                                       recipes: currentState.recipes,
                                       queue: queue ?? currentState.queue)
    }
    
    
}
