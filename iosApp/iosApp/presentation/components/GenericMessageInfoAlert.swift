//
//  GenericMessageInfoAlert.swift
//  iosApp
//
//  Created by Daniel Schmidt on 10.07.21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct GenericMessageInfoAlert {
    
    func build(
        message: GenericMessageInfo,
        onRemoveHeadMessage: @escaping () -> Void
    ) -> Alert {
        return Alert(
            title: Text(message.title).font(Font.custom("Comic Sans MS", size: 16)),
            message: Text(message.description_ ?? "Something went wrong").font(Font.custom("Comic Sans MS", size: 15)),
            primaryButton: .default(
                Text(message.positiveAction?.positiveBtnTxt ?? "OK")
                    .font(Font.custom("Comic Sans MS", size: 15)),
                action: {
                    if (message.positiveAction != nil) {
                        message.positiveAction!.onPositiveAction()
                    }
                    onRemoveHeadMessage()
                }),
            secondaryButton: .default(
                Text(message.negativeAction?.negativeBtnTxt ?? "Cancel")
                    .font(Font.custom("Comic Sans MS", size: 15)),
                action: {
                    if (message.negativeAction != nil) {
                        message.negativeAction!.onNegativeAction()
                    }
                    onRemoveHeadMessage()
                })
        )
    }
}

