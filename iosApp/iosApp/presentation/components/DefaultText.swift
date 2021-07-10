//
//  DefaultText.swift
//  iosApp
//
//  Created by Daniel Schmidt on 10.07.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct DefaultText: View {
    
    private let text: String
    private let size: CGFloat
    
    init(_ text: String, size: CGFloat = 15) {
        self.text = text
        self.size = size
    }
    
    var body: some View {
        Text(text)
            .font(Font.custom("Comic Sans MS", size: size))
    }
}

struct DefaultText_Previews: PreviewProvider {
    static var previews: some View {
        DefaultText("for rendering")
    }
}
