/// <reference types="Cypress" />

context('Actions', () => {
  beforeEach(() => {
    cy.visit('http://localhost:3000')
  })

  it('List all matches without filter', () => {
    cy.get('h6.MuiTypography-h6-60').should(($item) => {
      expect($item).to.have.length(25)
      expect($item.first()).to.contain('Caroline')
    })
  })

  it('Filter values by Has Photo', () => {
    cy.get('#hasPhoto').check()  
    cy.get('h6.MuiTypography-h6-60').should(($item) => {
      expect($item).to.have.length(22)
      expect($item.first()).to.contain('Caroline')
    })
  })

  it('Filter values by In Contact', () => {
    cy.get('#inContact').check()
    cy.get('h6.MuiTypography-h6-60').should(($item) => {
      expect($item).to.have.length(12)
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

})
