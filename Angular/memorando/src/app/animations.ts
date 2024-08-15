import { animate, state, style, transition, trigger } from '@angular/animations';

export const highlightedStateTrigger = trigger( 'highlightedState', [
    state('default', style({
        border: '2px solid #B2B6FF'
      })
    ),
    state('highlighted', style({
        border: '4px solid #B2B6FF',
        filter: 'brightness(92%)'
      })
    ),
    transition('default => highlighted', [
      animate('200ms cubic-bezier(0.68, -0.6, 0.32, 1.6)', style({ transform: 'scale(1.02)' })),
      animate(200),
    ]),
  ]
)

export const showStateTrigger = trigger('showState', [
    transition(':enter', [
      style({
        opacity: 0
      }),
      animate(300, style({
        opacity: 1
      })
      )
    ]),
    transition(':leave', [
      animate(300, style({
        opacity: 0
      })
      )
    ]),
  ]
)

export const buttonStateTrigger = trigger('buttonState', [
    transition('* => checked', [
      animate('400ms ease-in', style({
        transform: 'scale(1.1)'
      })),
    ])
  ]
)
