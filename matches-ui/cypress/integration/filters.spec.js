/// <reference types="Cypress" />

context('Actions', () => {
  beforeEach(() => {
    cy.visit('http://localhost:3000')
  })

  it('List all matches without filter', () => {
    cy.get('h6.MuiTypography-h6-60').should(($item) => {
      expect($item).to.have.length(8)
      expect($item.first()).to.contain('Caroline')
    })
  })

  it('Filter values by Has Photo', () => {
    cy.get('#hasPhoto').check()  
    cy.get('h6.MuiTypography-h6-60').should(($item) => {
      expect($item).to.have.length(8)
      expect($item.first()).to.contain('Caroline')
    })
  })

  it('Filter values by In Contact', () => {
    cy.get('#inContact').check()
    cy.get('h6.MuiTypography-h6-60').should(($item) => {
      expect($item).to.have.length(8)
      expect($item.first()).to.contain('Caroline')
    })
  })

  it('Filter values by Favourite', () => {
    cy.get('#favourite').check()
    cy.get('h6.MuiTypography-h6-60').should(($item) => {
      expect($item).to.have.length(6)
      expect($item.first()).to.contain('Caroline')
    })
  })

  it('Filter value values by name', () => {
    cy.get('#name').type('Kat')
    cy.get('h6.MuiTypography-h6-60').should(($item) => {
      expect($item).to.have.length(4)
      expect($item.eq(0)).to.contain('Kate')
      expect($item.eq(1)).to.contain('Katie')
      expect($item.eq(2)).to.contain('Katlin')
      expect($item.eq(3)).to.contain('Katherine')
    })
  })

  it('Paging test', () => {

    cy.get('h6.MuiTypography-h6-60').should(($item) => {
      expect($item).to.have.length(8)
      expect($item.eq(0)).to.contain('Caroline')
    })

    cy.get('span.MuiFlatPagination-label-315').contains('>').click()
    cy.get('h6.MuiTypography-h6-60').should(($item) => {
      expect($item).to.have.length(8)
      expect($item.eq(0)).to.contain('Caroline')
    })

    cy.get('span.MuiFlatPagination-label-315').contains('>').click()
    cy.get('h6.MuiTypography-h6-60').should(($item) => {
      expect($item).to.have.length(8)
      expect($item.eq(0)).to.contain('Samantha')
    })

    cy.get('span.MuiFlatPagination-label-315').contains('>').click()
    cy.get('h6.MuiTypography-h6-60').should(($item) => {
      expect($item).to.have.length(1)
      expect($item.eq(0)).to.contain('Susan')
    })

  })

})
